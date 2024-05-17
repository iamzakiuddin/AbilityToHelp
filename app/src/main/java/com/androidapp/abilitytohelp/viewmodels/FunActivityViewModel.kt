package com.androidapp.abilitytohelp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.abilitytohelp.model.AbbreviationsResponse
import com.androidapp.abilitytohelp.model.AntonymsSynonymsResponse
import com.androidapp.abilitytohelp.model.ConvoResponse
import com.androidapp.abilitytohelp.model.DictionaryResponse
import com.androidapp.abilitytohelp.model.LiteratureResponse
import com.androidapp.abilitytohelp.model.PartOfSpeechResponse
import com.androidapp.abilitytohelp.model.PeriodicElementResponse
import com.androidapp.abilitytohelp.model.PhrasesResponse
import com.androidapp.abilitytohelp.model.RiddleResponse
import com.androidapp.abilitytohelp.model.SynonymsResponse
import com.androidapp.abilitytohelp.model.WordImageResponse
import com.androidapp.abilitytohelp.network.NetworkResources
import com.androidapp.abilitytohelp.network.NetworkUtil
import com.androidapp.abilitytohelp.network.Repository
import kotlinx.coroutines.launch

class FunActivityViewModel : ViewModel(){

    var repository: Repository? = null

    private var synonymsResponse = MutableLiveData<NetworkResources<SynonymsResponse>>()
    var synonymsResponseObserver: LiveData<NetworkResources<SynonymsResponse>> = synonymsResponse

    private var wordImageResponse = MutableLiveData<NetworkResources<WordImageResponse>>()
    var wordImageResponseObserver: LiveData<NetworkResources<WordImageResponse>> = wordImageResponse

    private var riddleResponse = MutableLiveData<NetworkResources<RiddleResponse>>()
    var riddleResponseObserver: LiveData<NetworkResources<RiddleResponse>> = riddleResponse

    private var periodicElementsResponse = MutableLiveData<NetworkResources<PeriodicElementResponse>>()
    var periodicResponse : LiveData<NetworkResources<PeriodicElementResponse>> = periodicElementsResponse

    private var antonymsSynonymsResponse = MutableLiveData<NetworkResources<AntonymsSynonymsResponse>>()
    var antonymsSynResponse : LiveData<NetworkResources<AntonymsSynonymsResponse>> = antonymsSynonymsResponse

    private var partOfSpeechResponse = MutableLiveData<NetworkResources<PartOfSpeechResponse>>()
    var speechResponse : LiveData<NetworkResources<PartOfSpeechResponse>> = partOfSpeechResponse

    private var literatureApiResponse = MutableLiveData<NetworkResources<LiteratureResponse>>()
    var literatureResponse : LiveData<NetworkResources<LiteratureResponse>> = literatureApiResponse

    private var dictionaryApiResponse = MutableLiveData<NetworkResources<DictionaryResponse>>()
    var dictionaryResponse : LiveData<NetworkResources<DictionaryResponse>> = dictionaryApiResponse

    private var abbreviationsApiResponse = MutableLiveData<NetworkResources<AbbreviationsResponse>>()
    var abbreviationsResponse : LiveData<NetworkResources<AbbreviationsResponse>> = abbreviationsApiResponse

    private var phrasesApiResponse = MutableLiveData<NetworkResources<PhrasesResponse>>()
    var phrasesResponse : LiveData<NetworkResources<PhrasesResponse>> = phrasesApiResponse

    private var basicApiConvoResponse = MutableLiveData<NetworkResources<ConvoResponse>>()
    var basicConvoResponse : LiveData<NetworkResources<ConvoResponse>> = basicApiConvoResponse

    init {
        repository = NetworkUtil.provideRepository()
    }

    fun getSynonyms(word: String){
        synonymsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            synonymsResponse.value = repository?.getSynonyms(word)
        }
    }
    fun synonymsResponseObserver():LiveData<NetworkResources<SynonymsResponse>>{
        return synonymsResponseObserver
    }

    fun getWordImage(word: String){
        wordImageResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            wordImageResponse.value = repository?.getWordImage(word)
        }
    }
    fun wordImageResponseObserver():LiveData<NetworkResources<WordImageResponse>>{
        return wordImageResponseObserver
    }

    fun getRiddle(){
        riddleResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            riddleResponse.value = repository?.getRiddle()
        }
    }

    fun riddleResponseObserver(): LiveData<NetworkResources<RiddleResponse>>{
        return riddleResponseObserver
    }

    fun getPeriodicTable(){
        periodicElementsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            periodicElementsResponse.value = repository?.getPeriodicTable()
        }
    }

    fun periodicTableObserver() : LiveData<NetworkResources<PeriodicElementResponse>>{
        return periodicResponse
    }

    fun getAntonymSynonyms(word: String){
        antonymsSynonymsResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            antonymsSynonymsResponse.value = repository?.getAntonymsSynonyms(word)
        }
    }

    fun getAntonymsSynonymsObserver() : LiveData<NetworkResources<AntonymsSynonymsResponse>>{
        return antonymsSynResponse
    }

    fun getPartsOfSpeech(word: String){
        partOfSpeechResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            partOfSpeechResponse.value = repository?.getPartsOfSpeech(word)
        }
    }

    fun partsOfSpeechObserver() : LiveData<NetworkResources<PartOfSpeechResponse>>{
        return speechResponse
    }

    fun getLiteratureResponse(term: String){
        literatureApiResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            literatureApiResponse.value = repository?.getLiterature(term)
        }
    }

    fun getLiteratureObserver() : LiveData<NetworkResources<LiteratureResponse>>{
        return literatureResponse
    }

    fun getAbbreviationsResponse(term: String){
        abbreviationsApiResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            abbreviationsApiResponse.value = repository?.getAbbreviations(term)
        }
    }

    fun getAbbreviationsObserver() : LiveData<NetworkResources<AbbreviationsResponse>>{
        return abbreviationsResponse
    }

    fun getDictionaryResponse(word: String){
        dictionaryApiResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            dictionaryApiResponse.value = repository?.getDictionary(word)
        }
    }

    fun getDictionaryObserver() : LiveData<NetworkResources<DictionaryResponse>>{
        return dictionaryResponse
    }

    fun getPhraseResponse(phrase: String){
        phrasesApiResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            phrasesApiResponse.value = repository?.getPhrases(phrase)
        }
    }

    fun getPhraseObserver() : LiveData<NetworkResources<PhrasesResponse>>{
        return phrasesResponse
    }

    fun getBasicConversationList() {
        basicApiConvoResponse.value = NetworkResources.loading()
        viewModelScope.launch {
            basicApiConvoResponse.value = repository?.getBasicConversationList()
        }
    }

    fun getBasicConversationListObserver() : LiveData<NetworkResources<ConvoResponse>>{
        return basicConvoResponse
    }
}