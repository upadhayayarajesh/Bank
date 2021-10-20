import backend.accounts.exceptions.AccountNotFound;
import backend.managers.RequestManager;
import backend.people.exceptions.MemberNotFound;
import backend.people.exceptions.NotOldEnough;
import frontend.parser.Parser;
import frontend.parser.classes.Day;
import frontend.parser.classes.Request;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static RequestManager requestManager = new RequestManager();

    public static void processRequest(Request request) {
        switch (request.getRequestType()) {
            case JOIN:
                requestManager.processJoinRequest(request.getPerson(),
                        request.getRequestAmount());
                break;
            case QUIT:
                requestManager.processQuitRequest(request.getPerson());
                break;
            case DEPOSIT:
                try {
                    requestManager.processDepositRequest(request.getPerson(),
                            request.getRequestAmount());
                } catch (MemberNotFound memberNotFound) {
                    memberNotFound.printStackTrace();
                } catch (AccountNotFound accountNotFound) {
                    accountNotFound.printStackTrace();
                } catch (NotOldEnough notOldEnough) {
                    notOldEnough.printStackTrace();
                }
                break;
            case WITHDRAW:
                try {
                    requestManager.processWithdrawRequest(request.getPerson(),
                            request.getRequestAmount());
                } catch (MemberNotFound memberNotFound) {
                    memberNotFound.printStackTrace();
                } catch (AccountNotFound accountNotFound) {
                    accountNotFound.printStackTrace();
                } catch (NotOldEnough notOldEnough) {
                    notOldEnough.printStackTrace();
                }
                break;
            case NEWLOAN:
                requestManager.processNewLoanRequest(request.getPerson(),
                        request.getRequestAmount());
                break;
            case PAYLOAN:
                requestManager.processPayLoanRequest(request.getPerson(),
                        request.getRequestAmount());
                break;
            case ROB:
                requestManager.processRobbery(request.getPerson(),
                        request.getRequestAmount());
                break;
            case NONE:
                break;
        }
    }

    public static void main(String[] args) {
        String strDaysDir = "resources/days";
        File daysDir = new File(strDaysDir);
        File[] daysFiles = daysDir.listFiles();

        if (daysFiles != null) {
            int dayNum = 1;
            for (File file : daysFiles) {
                Day day = Parser.parse(dayNum, file.toPath());
                for (Request request : day.getRequests()) {
                    processRequest(request);
                    System.out.println();
                }
                // End of day
                double membershipFee = 5;
                //ThreadLocalRandom.current().nextDouble(1, 10);
                double interestRate = 0.1;
                //ThreadLocalRandom.current().nextDouble(0, 0.2);
                requestManager.processEndOfDay(membershipFee, interestRate);
                // Status report
                System.out.println("Bank status after day " + dayNum + ":");
                System.out.println();
                requestManager.report();
                dayNum++;
            }
        }
        else {
            System.out.println("Could not find directory: " + strDaysDir);
        }
    }
}
