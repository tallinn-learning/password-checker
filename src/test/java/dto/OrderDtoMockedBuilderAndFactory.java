package dto;

import lombok.Builder;
import utils.RandomDataGenerator;

@Builder
public class OrderDtoMockedBuilderAndFactory {
    private String status;
    private int courierId;
    private String customerName;
    private String customerPhone;
    private String comment;
    private int id;

    public static OrderDtoMockedBuilderAndFactory createRandomOrder() {
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