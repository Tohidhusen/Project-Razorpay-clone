package com.project.RazorpayClone.payment.processor.strategy;

import com.project.RazorpayClone.common.utility.RandomUtill;
import com.project.RazorpayClone.payment.processor.PaymentProcessor;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        final String VPA_CODE_FAIL="fail@Okaxis";
        String bankcode=request.methodDetails()!=null ?
                request.methodDetails().get("VPA").toString():null;

        //simulation
        if(VPA_CODE_FAIL.equals(bankcode)){
            return new PaymentProcessorResponse.Failure("BANK_REJECTED",
                    "Bank rejected the transaction registration");
        }
        String processorRef="UPI_PROCESSOR_"+ RandomUtill.randombase64(16);
        String bankRef="BANK_REF"+ RandomUtill.randombase64(16);

        return new PaymentProcessorResponse.Success(processorRef,bankRef);
        
    }
}
