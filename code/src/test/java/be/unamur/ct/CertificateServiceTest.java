package be.unamur.ct;

import be.unamur.ct.data.dao.CertificateDao;
import be.unamur.ct.data.service.CertificateService;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CertificateServiceTest {

    @TestConfiguration
    static class CertificateServiceTestContextConfiguration {

        @Bean
        public CertificateService certificateService() {
            return new CertificateService();
        }
    }


    @MockBean
    private CertificateDao certificateDao;

    @Autowired
    private CertificateService certificateService;

    @Before
    public void setupMock(){
        Mockito.when(certificateDao.countByVATIsNotNullAndVatSearched(true))
                .thenReturn(2);
        Mockito.when(certificateDao.countByVATIsNullAndVatSearched(true))
                .thenReturn(1);
        Mockito.when(certificateDao.countByVATIsNullAndVatSearched(false))
                .thenReturn(1);

        List<Object[]> issuer = new ArrayList<>();
        issuer.add(new Object[]{"Bob", BigInteger.valueOf(1)});
        issuer.add(new Object[]{"Alice", BigInteger.valueOf(3)});

        Mockito.when(certificateDao.distinctIssuer())
                .thenReturn(issuer);

        List<Object[]> algo = new ArrayList<>();
        algo.add(new Object[]{"Alg1", BigInteger.valueOf(3)});
        algo.add(new Object[]{"Alg2", BigInteger.valueOf(1)});

        Mockito.when(certificateDao.distinctAlgorithm())
                .thenReturn(algo);
    }


    @Test
    public void testVatGraphData(){

        ArrayList<Integer> result = certificateService.vatGraphData();


        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        expected.add(1);

        assertThat(result.size()).isEqualTo(expected.size());

        for(int i = 0; i < result.size(); i++){
            assertThat(result.get(i)).isEqualTo(expected.get(i));
        }

    }


    @Test
    public void testIssuerGraphData(){

        Pair<ArrayList<BigInteger>, ArrayList<String>> result = certificateService.issuerGraphData();


        ArrayList<BigInteger> num = new ArrayList<>();
        num.add(BigInteger.valueOf(3));
        num.add(BigInteger.valueOf(1));

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Alice");
        labels.add("Bob");

        Pair<ArrayList<BigInteger>, ArrayList<String>> expected = new Pair<>(num, labels);

        checkList(result, expected);
    }


    @Test
    public void testAlgorithmGraphData(){
        Pair<ArrayList<BigInteger>, ArrayList<String>> result = certificateService.algorithmGraphData();


        ArrayList<BigInteger> num = new ArrayList<>();
        num.add(BigInteger.valueOf(3));
        num.add(BigInteger.valueOf(1));

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Alg1");
        labels.add("Alg2");

        Pair<ArrayList<BigInteger>, ArrayList<String>> expected = new Pair<>(num, labels);

        checkList(result, expected);

    }

    private void checkList(Pair<ArrayList<BigInteger>, ArrayList<String>> result,
                           Pair<ArrayList<BigInteger>, ArrayList<String>> expected) {

        assertThat(result.getValue0().size()).isEqualTo(expected.getValue0().size());
        assertThat(result.getValue1().size()).isEqualTo(expected.getValue1().size());

        for (int i = 0; i < result.getValue0().size(); i++) {
            assertThat(result.getValue0().get(i)).isEqualTo(expected.getValue0().get(i));
            assertThat(result.getValue1().get(i)).isEqualTo(expected.getValue1().get(i));
        }
    }
}
