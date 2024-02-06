package project.vo;

public class MembersVo {
    public static String MEMBERS_TITLE = String.format("%5s %s %10s %20s %10s %4s",
        "고객코드", "이름", "이메일", "전화번호", "나이", "등급"
    );

    private String code;
    private String name;
    private String email;
    private String phoneNumber;
    private int age;
    private String rank;

    public MembersVo(String code, String name, String email, String phoneNumber) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public MembersVo(String code, String name, String email, String phoneNumber, int age) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public MembersVo(String code, String name, String email, String phoneNumber, int age, String rank) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.rank = rank;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public String getRank() {
        return rank;
    }


    @Override
    public String toString() {
        return String.format("%8s %3s %20s %20s %7d %6s",
            code, name, email, phoneNumber, age, rank
        );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MembersVo other = (MembersVo) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (age != other.age)
            return false;
        return true;
    }

}
