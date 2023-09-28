package pl.daniel.tokenprovider

class TokenProviderServiceImpl : TokenProviderService {

    override fun getToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyOGQ5MWJkYjAyNDUzYWM4YTQ2NWE1ZTc1ZjlkMmMwZSIsInN1YiI6IjY1MTMxY2RmOGE4NGQyMDBlMDVkNjg5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QfH5tSARey1LypWmlmmnBXBOfIfKRwU9d-cjmCwu86I"
    }

}