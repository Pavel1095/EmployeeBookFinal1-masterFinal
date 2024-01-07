package SkyPro.Stud.EmployeeBookFinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "В компании уже есть такой сотрудник")
public class EmployeeAlreadyAddedException extends  RuntimeException{

}
