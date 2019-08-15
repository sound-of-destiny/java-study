package Kilim;

import kilim.Mailbox;
import kilim.Task;
// 基于ant
public class CalculationCooperation {
    public static void main(String[] args) {
        Mailbox<Calculation> sharedMailbox = new Mailbox<>();

        Task deferred = new DeferredDivision(sharedMailbox);
        Task calculator = new Calculator(sharedMailbox);

        deferred.start();
        calculator.start();

    }
}
