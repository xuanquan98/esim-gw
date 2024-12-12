package com.quanvx.esim.response.joytel;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoytelResponse<T> {

   private String message;
   private int code;
   private T data;
}
