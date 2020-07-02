package br.com.fayoub.scheduling.transfer.model.enumerator;

public enum TransferType {
    A,
    B,
    C;
    
    private static int DAYS_THRESHOLD = 10;
    
    public static TransferType returnType(int days) {
        if (days == 0)
            return A;
        if (days <= DAYS_THRESHOLD)
            return B;
        
        return C;
    }
}
