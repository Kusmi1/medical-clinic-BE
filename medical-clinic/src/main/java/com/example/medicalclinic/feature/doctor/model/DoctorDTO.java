package com.example.medicalclinic.feature.doctor.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DoctorDTO {
    private UUID id;
    private String name;
    private String surname;

    public static DoctorDTO from(Doctor doctor) {
        DoctorDTO doctorInfoDTO = new DoctorDTO();
        doctorInfoDTO.setId(doctor.getId());
        doctorInfoDTO.setName(doctor.getName());
        doctorInfoDTO.setSurname(doctor.getSurname());
        return doctorInfoDTO;
    }
}