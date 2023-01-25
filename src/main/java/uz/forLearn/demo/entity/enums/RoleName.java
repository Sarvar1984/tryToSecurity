package uz.forLearn.demo.entity.enums;

public enum RoleName {
    ADMIN("Admin"),
    EXPERT("Mutahassis"),
    EMPLOYER("Buyurtmachi"),
    PHYSICAL_PERSON("Jismoniy_shaxs"),
    LEGAL_PERSON("Yuridik_shaxs");

    private final String description;

    RoleName(String description) {
        this.description = description;
    }


}
