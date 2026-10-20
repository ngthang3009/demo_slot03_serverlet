/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class InvoiceError {
    private String invIdError;
    private String invDateError;
    private String customerError;

    public InvoiceError() {
       invIdError = "";
       invDateError= "";
       customerError = "";
    }

    public String getInvIdError() {
        return invIdError;
    }

    public void setInvIdError(String invIdError) {
        this.invIdError = invIdError;
    }

    public String getInvDateError() {
        return invDateError;
    }

    public void setInvDateError(String invDateError) {
        this.invDateError = invDateError;
    }

    public String getCustomerError() {
        return customerError;
    }

    public void setCustomerError(String customerError) {
        this.customerError = customerError;
    }

    
    
}
