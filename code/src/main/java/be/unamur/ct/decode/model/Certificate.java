package be.unamur.ct.decode.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Entity class used to represent certificates in the application.
 * This class is used by JPA to create the corresponding SQL table in the database.
 * The class contains variables needed to represent a certificate and basic getters, setters and toString methods
 */
@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Length(min = 3)
    private String subject;

    private String issuer;
    private Date notAfter;
    private Date notBefore;

    @Column(name = "signature_alg")
    private String signatureAlg;
    private int versionNumber;
    private String VAT;
    private boolean vatSearched = false;

    public Certificate() {
    }

    public Certificate(@Length(min = 3) String subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    public String getSignatureAlg() {
        return signatureAlg;
    }

    public void setSignatureAlg(String signatureAlg) {
        this.signatureAlg = signatureAlg;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public boolean isVatSearched() {
        return vatSearched;
    }

    public void setVatSearched(boolean vatSearched) {
        this.vatSearched = vatSearched;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", issuer='" + issuer + '\'' +
                ", notAfter=" + notAfter +
                ", notBefore=" + notBefore +
                ", signatureAlg='" + signatureAlg + '\'' +
                ", versionNumber=" + versionNumber +
                ", VAT='" + VAT + '\'' +
                ", vatSearched=" + vatSearched +
                '}';
    }
}
