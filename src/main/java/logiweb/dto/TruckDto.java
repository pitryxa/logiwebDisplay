package logiweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckDto {
    private Integer all;
    private Integer free;
    private Integer broken;
}
