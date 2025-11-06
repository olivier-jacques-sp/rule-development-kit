package sailpoint;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import bsh.EvalError;
import bsh.Interpreter;



import sailpoint.rdk.utils.RuleXmlUtils;
import sailpoint.tools.GeneralException;


public class DateFunctionsTest {

    Logger log = LogManager.getLogger(DateFunctionsTest.class);
    private static final String RULE_FILENAME = "src/main/resources/rules/Rule - Generic - DateFunctions.xml";

 
@Test
public void testFarthestDate()throws GeneralException, EvalError{
    Interpreter i = new Interpreter();
    
    String result= "";
    
    String dateArray = "01/21/2025 10:00,01/21/2020 08:00,01/01/2020 08:00,12/12/2015 08:00";
    
    String inputFormat = "MM/dd/yyyy HH:mm";
    String outputFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
    String operation = "getFarthestDate";
    
    i.set("log", log);
    i.set("dateArray",dateArray);
    i.set("inputFormat",inputFormat);
    i.set("outputFormat",outputFormat);
    i.set("operation",operation);
    
    String source = RuleXmlUtils.readRuleSourceFromFilePath(RULE_FILENAME);
    result= (String) i.eval(source);

    log.info("Beanshell script returned " + result + " for operation " + operation);
}

@Test
public void testNearestDate() throws GeneralException, EvalError {
    Interpreter i = new Interpreter();

    String result = "";

    String dateArray = "01/21/2025 10:00,01/21/2020 08:00,01/01/2020 08:00,12/12/2015 08:00";

    String inputFormat = "MM/dd/yyyy HH:mm";
    String outputFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
    String operation = "getNearestDate";

    i.set("log", log);
    i.set("dateArray", dateArray);
    i.set("inputFormat", inputFormat);
    i.set("outputFormat", outputFormat);
    i.set("operation", operation);

    String source = RuleXmlUtils.readRuleSourceFromFilePath(RULE_FILENAME);
    result = (String) i.eval(source);

    log.info("Beanshell script returned " + result + " for operation " + operation);
}
}
