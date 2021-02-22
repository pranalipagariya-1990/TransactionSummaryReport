package com.file.util;

import kotlin.jvm.Throws;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertyFileUtilTest {

    PropertyFileUtil utility = new PropertyFileUtil();
    @Test
    public void shouldReturnCorrectCombinedColumnValue() throws IOException {
        String line = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O\n";
        String propertyName = "clientInformation";

        String combinedFieldValue = PropertyFileUtil.getCombinedValueForSummaryReportColumn(propertyName,line);
        Assertions.assertEquals(combinedFieldValue,"CL 432000000");
    }

    @Test
    public void shouldReturnGiveExceptionForIncorrectFileData(){
        String line = "315CL  43210";
        String propertyName = "productInformation";

        Assertions.assertThrows(IOException.class,() ->PropertyFileUtil.getCombinedValueForSummaryReportColumn(propertyName,line) );
    }

}
