package com.hjz.utils.mapstruct;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-14T19:26:15+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public CarVo carDtoToCarVo(CarDto carDto) {
        if ( carDto == null ) {
            return null;
        }

        CarVo carVo = new CarVo();

        carVo.setCarName( carDto.getName() );
        carVo.setId( carDto.getId() );

        return carVo;
    }
}
