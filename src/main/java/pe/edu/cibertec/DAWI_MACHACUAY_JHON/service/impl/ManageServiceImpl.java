package pe.edu.cibertec.DAWI_MACHACUAY_JHON.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto.CarDto;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.entity.Car;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.repository.CarRepository;
import pe.edu.cibertec.DAWI_MACHACUAY_JHON.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ManageServiceImpl  implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            CarDto carDto = new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getOwnerName());
            cars.add(carDto);
        });
        return cars;

    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCarId(),
                car.getMake(), car.getModel(),
                car.getOwnerName()));

    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(
                car.getCarId(), car.getMake(), car.getModel(),
                car.getYear(), car.getVin(), car.getLicensePlate(),
                car.getOwnerName(), car.getOwnerContact(), car.getPurchaseDate(),
                car.getMileage(), car.getEngineType(), car.getColor(), car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(), car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car -> {

            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setOwnerName(carDto.ownerName());
            carRepository.save(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean deleteCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);

    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        try {
            // Crear un nuevo objeto Car
            Car car = new Car();
            // No se establece el ID, ya que es autoincremental
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(new Date());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(new Date());
            car.setServiceDueDate(new Date());

            // Guardar el nuevo coche en la base de datos
            carRepository.save(car);
            return true; // Inserción exitosa
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace(); // O maneja la excepción de otra manera
            return false; // O lanza una excepción personalizada
        }
    }
    }

