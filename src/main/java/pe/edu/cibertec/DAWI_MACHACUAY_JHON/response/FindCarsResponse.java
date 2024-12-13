package pe.edu.cibertec.DAWI_MACHACUAY_JHON.response;

import pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars) {
}
