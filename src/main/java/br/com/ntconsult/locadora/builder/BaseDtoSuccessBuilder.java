package br.com.ntconsult.locadora.builder;

import br.com.ntconsult.locadora.base.BaseDto;
import br.com.ntconsult.locadora.base.StatusDto;
import org.springframework.http.HttpStatus;

public class BaseDtoSuccessBuilder<T> {

    private BaseDto<T> result;

    public BaseDtoSuccessBuilder(T result, HttpStatus status) {
        StatusDto statusDto = new StatusDto(status.value(), status.name());
        this.result = new BaseDto<T>(result, null, statusDto);
    }

    public BaseDto get() {
        return result;
    }

}
