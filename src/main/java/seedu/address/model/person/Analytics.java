package seedu.address.model.person;

import java.util.Date;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.person.LoanRecords;


public class Analytics {
    
    private int numLoans; // total number of loans
    private int numOverdueLoans; // total number of overdue loans
    private int numActiveLoans; // total number of active loans

    private float propOverdueLoans; // proportion of loans that are overdue over active loans
    private float propActiveLoans; // proportion of loans that are active over total loans

    private float totalValueLoaned; // total value of all loans
    private float totalValueOverdue; // total value of all overdue loans
    private float totalValueActive; // total value of all active loans

    private float averageLoanValue; // average loan value of all loans
    private float averageOverdueValue; // average loan value of all overdue loans
    private float averageActiveValue; // average loan value of all active loans

    private Date earliestLoanDate; // earliest loan date of all loans
    private Date earliestReturnDate; // earliest return date of active loans
    private Date latestLoanDate; // latest loan date of all loans
    private Date latestReturnDate; // latest return date of active loans

    private Analytics() {
        this.numLoans = 0;
        this.numOverdueLoans = 0;
        this.numActiveLoans = 0;

        this.propOverdueLoans = 0;
        this.propActiveLoans = 0;

        this.totalValueLoaned = 0;
        this.totalValueOverdue = 0;
        this.totalValueActive = 0;

        this.averageLoanValue = 0;
        this.averageOverdueValue = 0;
        this.averageActiveValue = 0;

        this.earliestLoanDate = null;
        this.earliestReturnDate = null;
        this.latestLoanDate = null;
        this.latestReturnDate = null;
    }

    public void updateNumFields(Loan loan) {
        this.numLoans++;
        if (loan.isOverdue()) {
            this.numOverdueLoans++;
        }
        if (loan.isActive()) {
            this.numActiveLoans++;
        }
    }

    public void updatePropFields() {
        if (this.numActiveLoans > 0) {
            this.propActiveLoans = (float) this.numActiveLoans / this.numLoans;
        }
        if (this.numLoans > 0) {
            this.propOverdueLoans = (float) this.numOverdueLoans / this.numLoans;
        }
    }

    public void updateValueFields(Loan loan) {
        this.totalValueLoaned += loan.getValue();
        if (loan.isOverdue()) {
            this.totalValueOverdue += loan.getValue();
        } 
        if (loan.isActive()) {
            this.totalValueActive += loan.getValue();
        }
    }

    public void updateAverageFields() {
        if (this.numActiveLoans > 0) {
            this.averageActiveValue = this.totalValueActive / this.numActiveLoans;
        }
        if (this.numOverdueLoans > 0) {
            this.averageOverdueValue = this.totalValueOverdue / this.numOverdueLoans;
        }
        if (this.numLoans > 0) {
            this.averageLoanValue = this.totalValueLoaned / this.numLoans;
        }
    }

    public void updateDateFields(Loan loan) {
        if (this.earliestLoanDate == null || loan.getStartDate().before(this.earliestLoanDate)) {
            this.earliestLoanDate = loan.getStartDate();
        }
        if (this.latestLoanDate == null || loan.getStartDate().after(this.latestLoanDate)) {
            this.latestLoanDate = loan.getStartDate();
        }
        if (!loan.isReturned()) {
            if (this.earliestReturnDate == null || loan.getReturnDate().before(this.earliestReturnDate)) {
                this.earliestReturnDate = loan.getReturnDate();
            }
            if (this.latestReturnDate == null || loan.getReturnDate().after(this.latestReturnDate)) {
                this.latestReturnDate = loan.getReturnDate();
            }
        }
    }
    
    public static Analytics getAnalytics(LoanRecords loanRecords) {
        Analytics analytics = new Analytics();
        analytics.numLoans = loanRecords.size();
        for (int i = 0; i < analytics.numLoans; i++) {
            Loan loan = loanRecords.getLoan(i);
            analytics.updateNumFields(loan);
            analytics.updateValueFields(loan);
            analytics.updateDateFields(loan);
        }
        analytics.updatePropFields();
        return analytics;
    }

    public int getNumLoans() {
        return numLoans;
    }

    public int getNumOverdueLoans() {
        return numOverdueLoans;
    }

    public int getNumActiveLoans() {
        return numActiveLoans;
    }

    public float getPropOverdueLoans() {
        return propOverdueLoans;
    }

    public float getPropActiveLoans() {
        return propActiveLoans;
    }

    public float getTotalValueLoaned() {
        return totalValueLoaned;
    }

    public float getTotalValueOverdue() {
        return totalValueOverdue;
    }

    public float getTotalValueActive() {
        return totalValueActive;
    }

    public float getAverageLoanValue() {
        return averageLoanValue;
    }

    public float getAverageOverdueValue() {
        return averageOverdueValue;
    }

    public float getAverageActiveValue() {
        return averageActiveValue;
    }

    public Date getEarliestLoanDate() {
        return earliestLoanDate;
    }

    public Date getEarliestReturnDate() {
        return earliestReturnDate;
    }

    public Date getLatestLoanDate() {
        return latestLoanDate;
    }

    public Date getLatestReturnDate() {
        return latestReturnDate;
    }

}