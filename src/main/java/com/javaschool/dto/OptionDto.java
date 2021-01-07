package com.javaschool.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OptionDto extends AbstractDto{

    private String name;
    private int price;
    private int serviceCost;
    //private Set<TariffDto> tariffs;

}
