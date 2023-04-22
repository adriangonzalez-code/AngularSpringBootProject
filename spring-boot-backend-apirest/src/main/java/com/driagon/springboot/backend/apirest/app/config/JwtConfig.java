package com.driagon.springboot.backend.apirest.app.config;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA4F27iiQ2FqZi/8XnkbgJrRMIhim3fhdjDngSGD/fIVGXw/N1\n" +
            "3cvKq7HdwWpqkDR+vs+KhmBru0S9KWAYW+OofkUSFwqO46Iowfb2ZhhclqoyNfBA\n" +
            "wI6GlQ7o+AdsjQk/EUTSFDOANUPUtDOI+Pu+C1EDJh0hCFGQzEeCy5jG1IxW6ZK4\n" +
            "/yX7gmuNeodDjYwon3myaX+Z3abd3FM0P9LIIyVIISFNC60LmicYsc2f4jJegMy8\n" +
            "ic8d9JSCMjJnmBrRfnEpLB12NZWqNHGvUQiWVdcTuF6epNwEtbed5269QKwu01BA\n" +
            "Uvl7vLi3FAX7b1PsnGaPzxvSER6lJl39LuWViQIDAQABAoIBAQCXzTNW+rsRxg+T\n" +
            "SB4uTFtXm1oYE9+PU1l0764mdAiOrXrGXFGx9/EvC4+jDlH9MVdT2G28Dz60nIRz\n" +
            "mw3AFh7pF+huaHlW8F8tJN9/Ihp64bpJHj1hSs0pysW7EqLSJaBjGbIdct7aqwMd\n" +
            "zQRumTzywSQlRG4WB8G1ROjswXg9U1YD/AMWpgQZAXRKpGUS5OO9DUyturyGy6cA\n" +
            "5VIoyVSghBByQzwzdVjJiLDMva8ZQExwzbb7DRrmxVNxcn7bYWprKuARJNgj8HvO\n" +
            "VUmAd+GzvwKnjPy3rsft0JNudSA6pUzXEfAZNURshpTnlkvyjMb+zgireO9BeC87\n" +
            "L40tFRIVAoGBAPbbkY/Dk2vfiGhNp5PajLvmVXaOTq9CQu7qlAfFiU4mswiyRr7h\n" +
            "jX0zD8sW0fkC5QcJEzJFeko0kYqG9UdPfnTZNW1AF2vfFL1vCXDssvdKXPaZqWE9\n" +
            "nR/1GRj541ZgWhVFNFV0zNqNUbCgUXFV6A8AJjOJrJf5YxGtvDkCH8pLAoGBAOis\n" +
            "7IyqMOM05+gWA0UIhRfsXP55DUIZseA98tEbTLIZWwzieu7Xr9ymmhNKi3CzUtQF\n" +
            "8l0P79mZaLkafMyyTy8JpieM30C2oLQPSJg9VSoH7Bc0aBf4YZmVp/mHLx2CO9Qw\n" +
            "3ot6cR3D/k3Z4eL/5I5lOvoS3If9ykVseuW+Z/r7AoGAYPRj+18JK7kjzjORudSA\n" +
            "qdwRfPrvlI4l08h2U4cWIoe4kZXTi2zzMVU4wr83wOGOKnE6BOic20BCWXIgK93h\n" +
            "AOu+vCYMDOYVM0HFJ4EP2WhvkrtPd03HxKFzNggPbgFx4CDKKTYyrzQJ/aE525Wu\n" +
            "CxY36s4L57SfYHYoEwvuiHkCgYBpsTN66kxuyTeW+3jcwPQEXAAaME7PefCNFlbY\n" +
            "mfgK5u2vOqnxGNTv/FX88V3KAgtb+J+Rmo2vYLpraxbJvS2vBkkNqjY3pT32zQKC\n" +
            "9UlcYGDkYY/vY4qiQ5McDEZgtyoKR3CwFxyPEtrP7mtnvCnMbTuPIUZAGA7r3k0t\n" +
            "U4LURQKBgQDAJGiJUZnsjQbRhQ2aErdQjtq0V1PC1YtRVDVXii9f1kYUshvvXRS6\n" +
            "NqCjCaXaWJGXHFN6RNbmx8cEvjJx9Rx9cGgMybEc+HGBEpyG3d6fXewUfww3z+/l\n" +
            "2090NG4MhQxHVUUfefqUXXMYboo3MtEWpjqXcwD3kQeCyYCksI/icQ==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4F27iiQ2FqZi/8XnkbgJ\n" +
            "rRMIhim3fhdjDngSGD/fIVGXw/N13cvKq7HdwWpqkDR+vs+KhmBru0S9KWAYW+Oo\n" +
            "fkUSFwqO46Iowfb2ZhhclqoyNfBAwI6GlQ7o+AdsjQk/EUTSFDOANUPUtDOI+Pu+\n" +
            "C1EDJh0hCFGQzEeCy5jG1IxW6ZK4/yX7gmuNeodDjYwon3myaX+Z3abd3FM0P9LI\n" +
            "IyVIISFNC60LmicYsc2f4jJegMy8ic8d9JSCMjJnmBrRfnEpLB12NZWqNHGvUQiW\n" +
            "VdcTuF6epNwEtbed5269QKwu01BAUvl7vLi3FAX7b1PsnGaPzxvSER6lJl39LuWV\n" +
            "iQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
