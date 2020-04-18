package be.unamur.ct.scrap.thread;

import be.unamur.ct.decode.model.Certificate;
import be.unamur.ct.scrap.service.VATScrapper;


/**
 * Thread class to start the scrapping of a single website
 */
public class VATScrapperThread extends Thread {
    private Certificate cert;
    private VATScrapper vatScrapper;


    /**
     * Constructor
     *
     * @author Jules Dejaeghere
     * @param cert        Certificate of the website to start scrapping for
     * @param vatScrapper Reference to the VATScrapper to use
     * @see Certificate
     */
    public VATScrapperThread(Certificate cert, VATScrapper vatScrapper) {
        this.cert = cert;
        this.vatScrapper = vatScrapper;
    }


    /**
     * Starts the VAT scrapping for the certificate saved in the variables of the instance
     *
     * @author Jules Dejaeghere
     */
    @Override
    public void run() {
        vatScrapper.scrap(cert);
    }

}
