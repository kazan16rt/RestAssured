public class Profile {
    private String name;
    private String about;

    public Profile() {
    }
    public Profile(String name, String about) {
        this.name = name;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    // опиши поля
    // добавь конструкторы — со всеми параметрами и без параметров
    // добавь геттеры и сеттеры для всех полей
}