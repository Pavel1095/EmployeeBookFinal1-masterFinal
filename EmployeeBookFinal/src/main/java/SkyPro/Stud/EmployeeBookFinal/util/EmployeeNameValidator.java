package SkyPro.Stud.EmployeeBookFinal.util;

import SkyPro.Stud.EmployeeBookFinal.exception.IllegalSymbolException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeNameValidator {

    public static void validaIsAlpha(String ... names) {
        for (String name : names){
            if (!StringUtils.isAlpha(name)) {
                throw new IllegalSymbolException();
            }
        }
    }
}
