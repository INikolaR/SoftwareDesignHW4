package ru.hse.BSE223.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class GetAllResponse {
    private List<Integer> ids;
}
