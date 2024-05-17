package com.androidapp.abilitytohelp.network

import com.androidapp.abilitytohelp.model.AbbreviationsResponse
import com.androidapp.abilitytohelp.model.AntonymsSynonymsResponse
import com.androidapp.abilitytohelp.model.ConvoResponse
import com.androidapp.abilitytohelp.model.DictionaryResponse
import com.androidapp.abilitytohelp.model.DictonaryResult
import com.androidapp.abilitytohelp.model.LiteratureResponse
import com.androidapp.abilitytohelp.model.PartOfSpeechResponse
import com.androidapp.abilitytohelp.model.PeriodicElementResponse
import com.androidapp.abilitytohelp.model.PhrasesResponse
import com.androidapp.abilitytohelp.model.PhrasesResult
import com.androidapp.abilitytohelp.model.RiddleResponse
import com.androidapp.abilitytohelp.model.SynonymsResponse
import com.androidapp.abilitytohelp.model.WordImageResponse
import com.google.gson.JsonObject
import org.json.JSONException
import java.io.IOException


class Repository(val dataApi: RestApi) {

    suspend fun getSynonyms(word: String): NetworkResources<SynonymsResponse> {
        try {
            val response = dataApi.getSynonyms(
                "https://synonyms-api.p.rapidapi.com/synonym",
                "b42a85a811msh4e571f6b5d07e65p102209jsneb74d08ea20f",
                "synonyms-api.p.rapidapi.com",
                word
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (!responseBody.synonyms.isNullOrEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else if (responseBody != null && responseBody.statusCode == 404) {
                    return NetworkResources.error(responseBody.message)
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }

    suspend fun getWordImage(word: String): NetworkResources<WordImageResponse> {
        try {
            val response = dataApi.getWordImage(
                "https://edupix.p.rapidapi.com/science/$word",
                "b42a85a811msh4e571f6b5d07e65p102209jsneb74d08ea20f",
                "edupix.p.rapidapi.com"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (responseBody.images.isNotEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }


    suspend fun getRiddle(): NetworkResources<RiddleResponse> {
        try {
            val response = dataApi.getRiddle(
                "b42a85a811msh4e571f6b5d07e65p102209jsneb74d08ea20f",
                "riddlie.p.rapidapi.com"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (!responseBody.riddle.isNullOrEmpty() && !responseBody.answer.isNullOrEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }

    suspend fun getPeriodicTable(): NetworkResources<PeriodicElementResponse> {
        try {
            val response = dataApi.getPeriodicTable(
                "https://periodictable.p.rapidapi.com/",
                "b42a85a811msh4e571f6b5d07e65p102209jsneb74d08ea20f",
                "periodictable.p.rapidapi.com"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (!responseBody.isNullOrEmpty() && responseBody.isNotEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }

    suspend fun getAntonymsSynonyms(word: String): NetworkResources<AntonymsSynonymsResponse> {
        try {
            val response = dataApi.getAntonymsSynonyms(
                "https://thesaurus-by-api-ninjas.p.rapidapi.com/v1/thesaurus/",
                "73c0c813a4mshebc31b8efca06b3p1dd4abjsn5b42d292097c",
                "thesaurus-by-api-ninjas.p.rapidapi.com",
                word
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (responseBody.word.isNotEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }

    suspend fun getPartsOfSpeech(word: String): NetworkResources<PartOfSpeechResponse> {
        try {
            val response = dataApi.getPartsOfSpeech(
                "https://speechfinder-word-class-identification.p.rapidapi.com/part_of_speech/",
                "73c0c813a4mshebc31b8efca06b3p1dd4abjsn5b42d292097c",
                "speechfinder-word-class-identification.p.rapidapi.com",
                word
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (!responseBody.word.isNullOrEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }

    suspend fun getPhrases(phrase: String): NetworkResources<PhrasesResponse> {
        try {
            val response = dataApi.getPhrases(
                "https://www.stands4.com/services/v2/phrases.php",
                "12440",
                "MVKNII8ViB8jzIST",
                phrase,
                "json"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    try {
                        val resultElement = responseBody["result"]
                        if (resultElement != null) {
                            val list = when {
                                resultElement.isJsonArray -> {
                                    val resultArray = resultElement.asJsonArray
                                    resultArray.mapNotNull { resultObject ->
                                        val example =
                                            resultObject.asJsonObject?.getStringOrNull("example")
                                                ?.replace("\\\"", "\"") ?: ""
                                        val explanation =
                                            resultObject.asJsonObject?.getStringOrNull("explanation")
                                                ?.replace("\\\"", "\"") ?: ""
                                        val term =
                                            resultObject.asJsonObject?.getStringOrNull("term")
                                                ?.replace("\\\"", "\"") ?: ""

                                        PhrasesResult(example, explanation, term)
                                    }.toMutableList()
                                }

                                resultElement.isJsonObject -> {
                                    val resultObject = resultElement.asJsonObject
                                    val example =
                                        resultObject.getStringOrNull("example")
                                            ?.replace("\\\"", "\"")
                                            ?: ""
                                    val explanation =
                                        resultObject.getStringOrNull("explanation")
                                            ?.replace("\\\"", "\"") ?: ""
                                    val term =
                                        resultObject.getStringOrNull("term")?.replace("\\\"", "\"")
                                            ?: ""
                                    mutableListOf(PhrasesResult(example, explanation, term))
                                }

                                else -> {
                                    null
                                }
                            }
                            return if (list.isNullOrEmpty()) {
                                NetworkResources.error("No data!")
                            } else {
                                NetworkResources.success(PhrasesResponse(list))
                            }
                        } else {
                            return NetworkResources.error("No data!")
                        }
                    } catch (e: JSONException) {
                        return NetworkResources.error("Something went wrong")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (io: IOException) {
            return NetworkResources.error(io.message ?: "Unknown error")
        } catch (e: Exception) {
            return NetworkResources.error(e.message ?: "Unknown error")
        }
    }


    suspend fun getAbbreviations(term: String): NetworkResources<AbbreviationsResponse> {
        try {
            val response = dataApi.getAbbreviations(
                "https://www.stands4.com/services/v2/abbr.php",
                "12440",
                "MVKNII8ViB8jzIST",
                term,
                "json"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (responseBody.result.isNotEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }

    suspend fun getDictionary(word: String): NetworkResources<DictionaryResponse> {
        try {
            val response = dataApi.getDictionary(
                "https://www.stands4.com/services/v2/defs.php",
                "12440",
                "MVKNII8ViB8jzIST",
                word,
                "json"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    try {
                        val resultElement = responseBody["result"]
                        if (resultElement != null) {
                            val list = when {
                                resultElement.isJsonArray -> {
                                    val resultArray = resultElement.asJsonArray
                                    resultArray.mapNotNull { resultObject ->
                                        val example =
                                            resultObject.asJsonObject?.getStringOrNull("example")
                                                ?.replace("\\\"", "\"") ?: ""
                                        val definition =
                                            resultObject.asJsonObject?.getStringOrNull("definition")
                                                ?.replace("\\\"", "\"") ?: ""
                                        val partOfSpeech =
                                            resultObject.asJsonObject?.getStringOrNull("partofspeech")
                                                ?.replace("\\\"", "\"") ?: ""
                                        val term =
                                            resultObject.asJsonObject?.getStringOrNull("term")
                                                ?.replace("\\\"", "\"") ?: ""

                                        DictonaryResult(definition, example, partOfSpeech, term)
                                    }.toMutableList()
                                }

                                resultElement.isJsonObject -> {
                                    val resultObject = resultElement.asJsonObject
                                    val example = resultObject.getStringOrNull("example")
                                        ?.replace("\\\"", "\"") ?: ""
                                    val definition = resultObject.getStringOrNull("definition")
                                        ?.replace("\\\"", "\"") ?: ""
                                    val partOfSpeech = resultObject.getStringOrNull("partofspeech")
                                        ?.replace("\\\"", "\"") ?: ""
                                    val term =
                                        resultObject.getStringOrNull("term")?.replace("\\\"", "\"")
                                            ?: ""
                                    mutableListOf(
                                        DictonaryResult(
                                            definition,
                                            example,
                                            partOfSpeech,
                                            term
                                        )
                                    )
                                }

                                else -> {
                                    null
                                }
                            }
                            return if (list.isNullOrEmpty()) {
                                NetworkResources.error("No data!")
                            } else {
                                NetworkResources.success(DictionaryResponse(list))
                            }
                        } else {
                            return NetworkResources.error("No data!")
                        }
                    } catch (e: JSONException) {
                        return NetworkResources.error("Something went wrong")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message ?: "Unknown error")
        }
    }

    suspend fun getLiterature(term: String): NetworkResources<LiteratureResponse> {
        try {
            val response = dataApi.getLiterature(
                "https://www.stands4.com/services/v2/literature.php",
                "12440",
                "MVKNII8ViB8jzIST",
                term,
                "json"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (responseBody.result.isNotEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }
    suspend fun getBasicConversationList(): NetworkResources<ConvoResponse> {
        try {
            val response = dataApi.getBasicConvo(
                "https://parseapi.back4app.com/classes/BasicConversation",
                "zlB2X4kOFTPRWS7pZov1TpYppn3aATx0D7Jl7Rw3",
                "GydDLwYtELP4dC0P3jY7wJQbDbJn6bfWr5avWQsd"
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && response.code() == 200) {
                    // Check if the response body is not empty
                    if (!responseBody.results.isNullOrEmpty()) {
                        return NetworkResources.success(responseBody)
                    } else {
                        return NetworkResources.error("No data!")
                    }
                } else {
                    return NetworkResources.error("No data!")
                }
            } else {
                return NetworkResources.error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResources.error(e.message!!)
        }
    }
}

fun JsonObject.getStringOrNull(key: String): String? {
    return this.get(key)?.takeIf { it.isJsonPrimitive }?.asString
}