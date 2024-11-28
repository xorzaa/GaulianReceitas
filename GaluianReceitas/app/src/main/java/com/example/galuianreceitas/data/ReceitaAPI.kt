// Interface da API
interface ReceitaAPI {
    @GET("recipes/findByIngredients")
    suspend fun buscarReceitas(
        @Query("ingredients") ingredientes: String,
        @Query("apiKey") apiKey: String = "4232f2948b0d46349fe316eebb87044d"
    ): Response<List<Receita>>
}