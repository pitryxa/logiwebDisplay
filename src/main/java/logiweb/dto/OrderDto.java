package logiweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private String truck;
    private List<String> drivers;
    private String status;
    private String currentWaypoint;
}
