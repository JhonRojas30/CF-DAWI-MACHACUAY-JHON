package pe.edu.cibertec.DAWI_MACHACUAY_JHON.dto;

import java.util.Date;

public record CarDetailDto(
                Integer carId,
                String make,
                String model,
                Integer year,
                String vin,
                String licensePlate,
                String ownerName,
                String ownerContact,
                Date purchaseDate,
                Integer mileage,
                String engineType,
                String color,
                String insuranceCompany,
                String insurancePolicyNumber,
                Date registrationExpirationDate,
                Date serviceDueDate

) {
}
