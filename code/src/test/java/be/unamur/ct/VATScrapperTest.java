package be.unamur.ct;


import be.unamur.ct.scrap.service.VATScrapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


/*
 * The database specified in the application.properties file should be running in order to run these test.
 * These tests will initialize the complete ApplicationContext to run, including the database
 * No changes will be made to the database, the application will only try to connect
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VATScrapperTest {

    @TestConfiguration
    static class VATScrapperTestContextConfiguration {

        @Bean
        public VATScrapper vatScrapper() {
            return new VATScrapper();
        }
    }


    @Autowired
    private VATScrapper vatScrapper;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8080));


    @Before
    public void setup() throws IOException {

        //  Creating small test web server to serve web pages to scrap
        InputStream simpleInput = getClass().getClassLoader().getResourceAsStream("html/simple/index.html");
        String simpleHtml = new String(IOUtils.toByteArray(simpleInput), Charset.forName("UTF-8"));

        wireMockRule.stubFor(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withBody(simpleHtml)
                        .withHeader("Content-Type", "text/html; charset=UTF-8")
                )
        );


        InputStream complexInput;
        String complexHtml;

        for(String s : new String[]{"index", "page-1", "page-2"}) {
            complexInput = getClass().getClassLoader().getResourceAsStream("html/complex/" + s + ".html");
            complexHtml = new String(IOUtils.toByteArray(complexInput), Charset.forName("UTF-8"));
            wireMockRule.stubFor(get(urlEqualTo("/complex/" + s))
                    .willReturn(aResponse()
                            .withBody(complexHtml)
                            .withHeader("Content-Type", "text/html; charset=UTF-8"))
            );
        }

        for(String s : new String[]{"wrong-checksum", "wrong-format"}) {
            complexInput = getClass().getClassLoader().getResourceAsStream("html/wrong/" + s + ".html");
            complexHtml = new String(IOUtils.toByteArray(complexInput), Charset.forName("UTF-8"));
            wireMockRule.stubFor(get(urlEqualTo("/wrong/" + s))
                    .willReturn(aResponse()
                            .withBody(complexHtml)
                            .withHeader("Content-Type", "text/html; charset=UTF-8"))
            );
        }

    }


    @Test
    public void testSearchPage() throws IOException, InterruptedException {

        String vat;

        // Easy case: VAT on first page
        vat = vatScrapper.searchPage(new URL("http://localhost:8080/"), 0, new HashSet<>());
        assertThat(vat).isEqualTo("BE0542703815");


        // VAT not on first page and depth too small
        vat = vatScrapper.searchPage(new URL("http://localhost:8080/complex/index"), 0, new HashSet<>());
        assertNull(vat);


        // VAT not on first page and depth large enough to find it on second page
        vat = vatScrapper.searchPage(new URL("http://localhost:8080/complex/index"), 3, new HashSet<>());
        assertThat(vat).isEqualTo("BE0542703815");

        // VAT not found because of wrong format
        vat = vatScrapper.searchPage(new URL("http://localhost:8080/wrong/wrong-format"), 0, new HashSet<>());
        assertNull(vat);

        // VAT not found because of wrong checksum
        vat = vatScrapper.searchPage(new URL("http://localhost:8080/wrong/wrong-checksum"), 0, new HashSet<>());
        assertNull(vat);
    }


    @Test
    public void testNormalizeVAT(){
        String[] raw = {"BE0123 456 346", "BE0123-456-346", "BE0123.456.346",
                        "0123 456 346", "0123456346", "0123.456.346"};

        String normalized;
        for(String vat : raw){
            normalized = vatScrapper.normalizeVAT(vat);
            assertThat(normalized).isEqualTo("BE0123456346");
        }

    }


    @Test
    public void testIsValidVAT(){
        String[] valid = {"BE0666679317", "BE0457741515", "BE0843370953"};
        String[] invalid = {"BE0666679300", "BE0457741542", "BE0843370973"};

        for(String vat : valid){
            assertTrue(vatScrapper.isValidVAT(vat));
        }

        for(String vat : invalid){
            assertFalse(vatScrapper.isValidVAT(vat));
        }
    }

}
