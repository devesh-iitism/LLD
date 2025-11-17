package factory;

import enums.PaymentMethod;
import strategy.CreditCardProcessor;
import strategy.PayPalProcessor;
import strategy.PaymentProcessor;
import strategy.UPIProcessor;

public class PaymentProcessorFactory {
    public static PaymentProcessor getProcessor(PaymentMethod method) {
        switch (method) {
            case CREDIT_CARD:
                return new CreditCardProcessor();
            case UPI:
                return new UPIProcessor();
            case PAYPAL:
                return new PayPalProcessor();
            // case BANK_TRANSFER:
            //     return new BankTransferProcessor();
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }
}
