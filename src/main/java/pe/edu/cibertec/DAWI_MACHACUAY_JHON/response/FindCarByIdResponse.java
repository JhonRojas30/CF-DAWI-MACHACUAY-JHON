package pe.edu.cibertec.DAWI_MACHACUAY_JHON.response;

import pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto car) {
}
