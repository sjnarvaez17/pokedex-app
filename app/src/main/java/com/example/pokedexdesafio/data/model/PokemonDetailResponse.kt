package com.example.pokedexdesafio.data.model

import android.os.Parcelable
import com.example.pokedexdesafio.domain.model.AbilityDetail
import com.example.pokedexdesafio.domain.model.TypeDetail
import com.example.pokedexdesafio.domain.model.AbilityList
import com.example.pokedexdesafio.domain.model.MoveDetail
import com.example.pokedexdesafio.domain.model.MoveList
import com.example.pokedexdesafio.domain.model.TypeList
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailResponse(
    val id: Int?,
    val name: String?,
    val types: List<TypeContainer?>,
    // no info about evolve
    val moves: List<MoveContainer?>,
    val abilities: List<AbilityContainer?>,
    val locationAreaEncounters: String?
) : Parcelable

//Type
@Parcelize
data class TypeContainer(val slot: Int?, val type: Type?) : Parcelable

@Parcelize
data class Type(val name: String?, val url: String?) : Parcelable

//Attacks
@Parcelize
data class MoveContainer(val move: Move?) : Parcelable

@Parcelize
data class Move(val name: String?, val url: String?) : Parcelable

//Abilities
@Parcelize
data class AbilityContainer(val ability: Ability?, val isHidden: Boolean?, val slot: Int?) : Parcelable

@Parcelize
data class Ability(val name: String?, val url: String?) : Parcelable

// Ext functions for mapping into business models
fun Ability.toAbility(): AbilityDetail? =
    if (name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        AbilityDetail(name, url)
    }

fun AbilityContainer.toAbilityList(): AbilityList? =
    if(ability != null || isHidden != null || slot != null){
        AbilityList(ability?.toAbility(), isHidden, slot)
    }else{
        null
    }
fun PokemonDetailResponse.toAbilitiesList(): List<AbilityList> = abilities.mapNotNull { it?.toAbilityList() }

fun Move.toMove(): MoveDetail? =
    if (name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        MoveDetail(name, url)
    }

fun MoveContainer.toMoveList(): MoveList? =
    if(move != null){
        MoveList(move.toMove())
    }else{
        null
    }

fun PokemonDetailResponse.toMovesList(): List<MoveList> = moves.mapNotNull { it?.toMoveList() }

fun Type.toType(): TypeDetail? =
    if (name.isNullOrBlank() || url.isNullOrBlank()) {
        null
    } else {
        TypeDetail(name, url)
    }

fun TypeContainer.toTypeList(): TypeList? =
    if(type != null && slot !=null){
        TypeList(slot, type.toType())
    }else{
        null
    }

fun PokemonDetailResponse.toTypesList(): List<TypeList> = types.mapNotNull { it?.toTypeList() }

//fun PokemonDetailResponse.toPokemonDetail(): PokemonDetail? =
//    if (name.isNullOrBlank() || types.isEmpty() || moves.isEmpty() || abilities.isEmpty() || locationAreaEncounters.isNullOrBlank()) {
//        null
//    } else {
//
//        var pokemonDetailTypeList: TypeList
//        var typePokemonDetail: TypeDetail
//        var listTypeList: MutableList<TypeList>
//        types.forEach {
//            if(it?.type != null){
//                typePokemonDetail = TypeDetail(it.type.name, it.type.url)
//                pokemonDetailTypeList = TypeList(it.slot, typePokemonDetail)
//                listTypeList.add(pokemonDetailTypeList)
//            }
//        }
//        var moveDetail: MoveDetail
//        var listMoveDetail: MoveList
//        var movesList: MutableList<MoveList>
//        moves.forEach {
//            if(it?.move != null){
//                moveDetail = MoveDetail(it.move.name, it.move.url)
//                listMoveDetail = MoveList(moveDetail)
//                movesList.add(listMoveDetail)
//            }
//        }
//        var abilityDetail: AbilityDetail
//        var listAbilityList: AbilityList
//        var abilityList: MutableList<AbilityList>
//        abilities.forEach {
//            if (it?.ability!=null){
//                abilityDetail = AbilityDetail(it.ability.name, it.ability.url)
//                listAbilityList = AbilityList(abilityDetail, it.isHidden, it.slot)
//                abilityList.add(listAbilityList)
//            }
//        }
//
//        PokemonDetail(id, name, listTypeList, movesList,abilityList, locationAreaEncounters)
//    }
