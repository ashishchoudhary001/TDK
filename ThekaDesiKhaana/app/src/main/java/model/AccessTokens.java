package model;

/**
 * Created by ParmeshMahore on 2/15/17.
 */

public class AccessTokens {

    private String acessToken;

    private String refreshTokenExpiry;

    private String refreshToken;

    private String accessTokenExpiry;

    public String getAcessToken() {
        return acessToken;
    }

    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }

    public String getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public void setRefreshTokenExpiry(String refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessTokenExpiry() {
        return accessTokenExpiry;
    }

    public void setAccessTokenExpiry(String accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
    }

    @Override
    public String toString() {
        return "AccessTokens [acessToken = " + acessToken + ", refreshTokenExpiry = " + refreshTokenExpiry + ", refreshToken = " + refreshToken + ", accessTokenExpiry = " + accessTokenExpiry + "]";
    }
}
