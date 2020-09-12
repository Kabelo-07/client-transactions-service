package com.moeketsi.lefa.assessment.clienttransactions.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {

    public static final String FIRSTNAME_NON_NULL = "First name cannot be empty or null";
    public static final String LASTNAME_NON_NULL = "Last name cannot be empty or null";
    public static final String MOBILE_NUMBER_NON_NULL = "Mobile number cannot be empty or null";
    public static final String VALID_SA_MOBILE_NUMBER = "Must be a valid SA Mobile Number";


    public static final String VALID_AMOUNT = "Amount must be a valid decimal";
    public static final String TRANSACTION_AMOUNT_SIZE_MUST = "Amount must be a valid decimal";
    public static final String NON_NULL = "ID number must be 13 digits";


    public static final String VALID_RSA_ID_NUMBER = "Must be a valid South African Id Number";
    public static final String VALIDATE_RSA_ID_NUMBER_REGEX = "(((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229))" +
            "(( |-)(\\d{4})( |-)(\\d{3})|(\\d{7}))";
    public static final String VALIDATE_MOBILE_NUMBER_REGEX = "^27[0-9]{9}";

    public static final String CLIENT_FIELD_ALREADY_EXISTS_RESULT_MESSAGE = "Client idNumber, mobile number or firstname already exists";
    public static final String CLIENT_SUCCESSFUL_RESULT_MESSAGE = "Client Successfully added";
    public static final String FAILED_TO_ADD_CLIENT_RESULT_MESSAGE = "Failed to add client";
    public static final String NO_CLIENT_DETAILS_FOUND_RESULT_MESSAGE = "No Client Details found";
    public static final String TRANSACTIONS_SUCCESSFULLY_ADDED_RESULT_MESSAGE = "Transactions successfully added";
    public static final String FAILED_TO_TRANSACTIONS_ESULT_MESSAGE = "Failed to add transactions";
    public static final String REQUEST_VALIDATION_ERROR_TITLE = "Request Validation Error";
    public static String DB_ERROR_MESSAGE = "Failed to complete db operation";
    public static String SERVICE_UNAVAILABLE_ERROR_MESSAGE = "Service is currently unavailable please try again later";

}
