package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import utils.RandomDataGenerator;

@Builder
@Getter
@Setter
public class OrderDtoMockedBuilderAndFactory {
    private String status;
    private int courierId;
    private String customerName;
    private String customerPhone;
    private String comment;
    private int id;

//    public String getCustomerName() {
//        return customerName;
//    }

    // static
  public static OrderDtoMockedBuilderAndFactory createRandomOrder() {
      // builder
      return OrderDtoMockedBuilderAndFactory.builder()
              .status("OPEN")
              .courierId(0)
              .customerName(RandomDataGenerator.generateName())
              .customerPhone("12343456")
              .comment("comment")
              .id(1)
              .build();
  }
}

