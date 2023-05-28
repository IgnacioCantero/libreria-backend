package com.libreria.backend.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEpAIBAAKCAQEAy4RVhknbncaePDk692HSqpN+Z1MsYqM/BrDDRP4Tb2knhOKk\n"
			+ "N3z0nsb/85S8BeoCCdU3M9D6zXKSFB+FSbuNB6ZIujqjxlpo7k1PwYfy2ezjJK0u\n"
			+ "5DcivDQ518bWyVbjRKcHce3+xfBzgxwC0+FJq/WQ7bax7eeqpLraj3cN9LYorolV\n"
			+ "wEifC7niuwSgX8lwW+ZTeb7x5IP8m/kKaFjpAi4SLYzoFl2AiMacwU7hF8M2YZM0\n"
			+ "hCU0y2VnZqsuKC270IAYCl0eGEb9eKr4JmgemCTa1nJDEGztx4tcyFzB/Zr2fuwN\n"
			+ "bua1YRZ1E7a/S4/mW8WUzSvO3usURwro3rLNkQIDAQABAoIBAQDKo1scuNGJCUho\n"
			+ "ontJ8gb2fKFxc+lP1Udd/AaR3YGwfr0wnRHxheNzbKVQ/RkxskyRSdELMNtoUNmO\n"
			+ "W8w1HB9SLIlhlB2Z1E+CZ0wwYJK31hxcWmvEI/nb9wxZAsy98Fud8cmB8PyqTb5W\n"
			+ "Hz/ZhAn8xCen6S7EuNS57KrOCjbFHgtOCutAm5L4v/MjX4JHP2wWTItIXyiNRn93\n"
			+ "A8jHzV1hMOReerWwtYxMQcW4WvZTceLsf3RwobCwYnOth78MHnEgDeJwNQ4mfcW3\n"
			+ "/tfUXI+JKzW65RQmYd1Q0Xbg8z1G0DCNa8IxqldQKxEhiPS8QveFDPYfPXvpAsSO\n"
			+ "B99nCUgBAoGBAPRXeDyaRWWUFpeqmlk7ovgmF0VJYV/lDLJTjFpQ5nhZEQzQQmdh\n"
			+ "TMqsd/7oezbDT/F2t51Q5jiCjFW8C/YvCCwE8xQi+8KBeHihUuKs8lvGxjy3ytyc\n"
			+ "AWnI+LqtT4eKlsfYQWkXSMS2D5DEopUQslUyxaZmqbOMMbXVxdBgaNvBAoGBANU6\n"
			+ "NRV+ddQhr7DVMmXvUJE/q0k0daOWaINDrjZ55L1jE5ltBL+zCTye8vTmmCYWXqvb\n"
			+ "3rOwiuCQPJE1Il/fO4a/Fkacxa29wYBKkHG74oq/4JpodeTgOD/Sy6ExofwWRIww\n"
			+ "F7bgnE/latUZuU3e6JF/WpMTtUqOQkWvMuMAdKXRAoGAFFG51ABYSQw5nL85OrZ0\n"
			+ "5QH16XMiv+DCX4oHH/OeVt/BRTmEbChxRGF3USFwLausWD4xIsn5ezWQCgkrTXu3\n"
			+ "yEFZHZGTMiemJxHzXJpZRTyQe7glDJeEnpFx7cQ/2tuxkkjvl5eyG3mwba+Mfaz3\n"
			+ "x5IDUcUz05STBbYTTzLVRYECgYEAv1tojmyaVBETOjBq9V4rQN8R2jWxKWlvS/Yu\n"
			+ "X4dPB8Plr/hNnbiQHo5LXgugGfZTagp9N2fFirkHEg3MVpvhMRFGbYJ+SWkVQgwo\n"
			+ "y1khewGuEQSUYR30fRqxUm1XgQN+6wyyC5ZR0cYC9VILod3de83+PCYgudMnmKAd\n"
			+ "m+kpxjECgYAFd97vsp7oDWAmjlXfLmPISc9pTSEmk8t1N2hDs+aOhOe0Kvjnt1yN\n"
			+ "qErKE29itv2UMjkb7Tp5tB10ewzNmbsGe9fBzU9nTVSK168G2gy+DKxXrfdflfL6\n"
			+ "ZdHtDvYxkFARI2uAnMIQ7XzLvz33j6ogeyIArIPBCZ/orEewwrDhJA==\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy4RVhknbncaePDk692HS\n"
			+ "qpN+Z1MsYqM/BrDDRP4Tb2knhOKkN3z0nsb/85S8BeoCCdU3M9D6zXKSFB+FSbuN\n"
			+ "B6ZIujqjxlpo7k1PwYfy2ezjJK0u5DcivDQ518bWyVbjRKcHce3+xfBzgxwC0+FJ\n"
			+ "q/WQ7bax7eeqpLraj3cN9LYorolVwEifC7niuwSgX8lwW+ZTeb7x5IP8m/kKaFjp\n"
			+ "Ai4SLYzoFl2AiMacwU7hF8M2YZM0hCU0y2VnZqsuKC270IAYCl0eGEb9eKr4Jmge\n"
			+ "mCTa1nJDEGztx4tcyFzB/Zr2fuwNbua1YRZ1E7a/S4/mW8WUzSvO3usURwro3rLN\n"
			+ "kQIDAQAB\n"
			+ "-----END PUBLIC KEY-----";

}
