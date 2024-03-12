package exercicioFixacao.Services;

import exercicioFixacao.entities.Contract;
import exercicioFixacao.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;
    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }
    public void processContract(Contract contract, int months){

        double basecQuota = contract.getTotalValue() / months;

         for (int i = 1; i <= months; i++){
             LocalDate dueDate =  contract.getDate().plusMonths(i);
             double interest = onlinePaymentService.interest(basecQuota, i);
             double fee =  onlinePaymentService.paymentFee(basecQuota + interest);
             double quota = basecQuota + interest + fee;

             contract.getInstallments().add(new Installment(dueDate,quota));

         }
    }
}
