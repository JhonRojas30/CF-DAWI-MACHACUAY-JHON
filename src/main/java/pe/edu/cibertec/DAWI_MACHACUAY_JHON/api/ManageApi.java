package pe.edu.cibertec.DAWI_MACHACUAY_JHON.api;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto.CarDto;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.response.*;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value="id",defaultValue = "0")String id){

        try{

            if(Integer.parseInt(id)>0){
                Optional<CarDto> optional= manageService.getAllCarsById(Integer.parseInt(id));
                if(optional.isPresent()){
                CarDto carDto=optional.get();
                return new FindCarsResponse("01","", List.of(carDto));
                }else{
                    return new FindCarsResponse("02","Car not found", null);
                }
            }else{
            List<CarDto> cars=manageService.getAllCars();
            if(!cars.isEmpty())
                return new FindCarsResponse("01","", cars);
            else
                return new FindCarsResponse("03","Car List not found", cars);

            }
        }catch (Exception e){

            e.printStackTrace();
            return new FindCarsResponse("99","Service not found", null);
        }

    }

    @GetMapping("/detail")
    public FindCarByIdResponse findCarById(@RequestParam(value="id",defaultValue = "0")String id){

        try{
            if(Integer.parseInt(id)>0){
            Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id));
                if(optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdResponse("01", "", carDetailDto);
                }
                else{
                    return new FindCarByIdResponse("02", "Car not found", null);

                }
            }else
                return new FindCarByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdResponse("99","service not found",null);
        }

    }

    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto){

        try{
            if(manageService.updateCar(carDto)) {

                return new UpdateCarResponse("01", "");
            } else{

                return new UpdateCarResponse("02", "User not found");
            }

        } catch (Exception e) {

            e.printStackTrace();
            return new UpdateCarResponse("99","Service not found");
        }
    }

    @PostMapping("/insert")
    public InsertCarResponse insertCar(@RequestBody CarDetailDto carDetailDto) {
        try {
            if (manageService.addCar(carDetailDto)) {
                return new InsertCarResponse("01", "Car inserted successfully");
            } else {
                return new InsertCarResponse("02", "Failed to insert car");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new InsertCarResponse("99", "Service not found");
        }
    }

    @DeleteMapping("/delete")
    public DeleteCarResponse deleteCar(@RequestParam(value = "id") int id) {
        try {
            if (manageService.deleteCarById(id)) {
                return new DeleteCarResponse("01", "Car deleted successfully");
            } else {
                return new DeleteCarResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "Service not found");
        }
    }

}
