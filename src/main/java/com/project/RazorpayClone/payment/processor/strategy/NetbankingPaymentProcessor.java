package com.project.RazorpayClone.payment.processor.strategy;

import com.project.RazorpayClone.common.utility.RandomUtill;
import com.project.RazorpayClone.payment.processor.PaymentProcessor;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorRequest;
import com.project.RazorpayClone.payment.processor.dto.PaymentProcessorResponse;

public class NetbankingPaymentProcessor implements PaymentProcessor {


    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        final String BANK_CODE_FAIL="BANK_CODE_FAIL";
        String bankcode=request.methodDetails()!=null ?
                request.methodDetails().get("BANK").toString():null;

        //simulation
        if(BANK_CODE_FAIL.equals(bankcode)){
            return new PaymentProcessorResponse.Failure("BANK_REJECTED",
                       "Bank rejected the transaction registration");
        }
        String processorRef="NBK_PROCESSOR_"+ RandomUtill.randombase64(16);
        String redirectRef="http://REDIRECT_BANK.COM/"+processorRef;

        return new PaymentProcessorResponse.Success(processorRef,redirectRef);
    }
}
