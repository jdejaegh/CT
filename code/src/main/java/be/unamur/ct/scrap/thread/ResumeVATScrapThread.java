package be.unamur.ct.scrap.thread;

import be.unamur.ct.scrap.service.VATScrapper;


/**
 * Thread class to resume VAT scrapping when the application is restarted
 */
public class ResumeVATScrapThread extends Thread {

    private VATScrapper vatScrapper;

    /**
     * Constructor
     *
     * @author Jules Dejaeghere
     * @param vatScrapper Reference to the VATScrapper to use
     */
    public ResumeVATScrapThread(VATScrapper vatScrapper) {
        this.vatScrapper = vatScrapper;
    }


    /**
     * Resumes the VATScrapping
     *
     * @author Jules Dejaeghere
     */
    @Override
    public void run() {
        vatScrapper.resumeVatScrapping();
    }
}
