package core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum HeaderValues {


    CONTENT_TYPE_JSON("Content-Type", "application/json");


    private String key;
    private String value;


}
