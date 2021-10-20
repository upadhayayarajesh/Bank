package frontend.parser.classes;

/**
 * Type of request being made.
 */
public enum RequestType {
    JOIN, QUIT,
    DEPOSIT, WITHDRAW,
    NEWLOAN, PAYLOAN,
    ROB,
    NONE;

    public static RequestType fromString(String requestType) {
        RequestType rt;
        switch (requestType.toUpperCase()) {
            case "JOIN":
                rt = JOIN;
                break;
            case "QUIT":
                rt = QUIT;
                break;
            case "DEPOSIT":
                rt = DEPOSIT;
                break;
            case "WITHDRAW":
                rt = WITHDRAW;
                break;
            case "NEWLOAN":
                rt = NEWLOAN;
                break;
            case "PAYLOAN":
                rt = PAYLOAN;
                break;
            case "ROB":
                rt = ROB;
                break;
            default:
                rt = NONE;
                break;
        }
        return rt;
    }
}
