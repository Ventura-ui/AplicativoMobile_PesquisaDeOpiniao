package br.edu.ifsp.dmo.pesquisadeopiniao.data.database

import android.content.ContentValues
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto

class VotoDao(private val dbHelper: DatabaseHelper) {

    fun inserirVoto(voto: Voto) : Long{
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO, voto.codigo)
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_VALOR, voto.valor)
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO_ESTUDANTE, voto.codigo_estudante)
        }

        return db.insert(DatabaseHelper.DATABASE_KEYS.TABLE_VOTO_NAME, null, values)
    }

    fun getAllVotos() : List<Voto>{
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_VALOR,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO_ESTUDANTE
        )

        val cursor = db.query(
            DatabaseHelper.DATABASE_KEYS.TABLE_VOTO_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val votos = mutableListOf<Voto>()

        cursor.use {
            while (it.moveToNext()){
                votos.add(
                    Voto(codigo = it.getString(0), valor = it.getInt(1), codigo_estudante = it.getString(2))
                )
            }
        }

        return votos
    }

    fun getVotoByCodigo(codigo: String) : Voto?{
        val voto: Voto?
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_VALOR,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO_ESTUDANTE
        )

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO} = ?"
        val whereArgs = arrayOf(codigo)

        var cursor = db.query(
            DatabaseHelper.DATABASE_KEYS.TABLE_VOTO_NAME,
            columns,
            where,
            whereArgs,
            null,
            null,
            null
        )

        cursor.use {
            voto = if(cursor.moveToNext()){
                Voto(cursor.getString(0), cursor.getInt(1), cursor.getString(2))
            }else{
                null
            }
        }

        return voto
    }

    fun getVotoByProntuario(prontuario: String) : Voto?{
        val voto: Voto?
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_VALOR,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO_ESTUDANTE
        )

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTO_CODIGO_ESTUDANTE} = ?"
        val whereArgs = arrayOf(prontuario)

        var cursor = db.query(
            DatabaseHelper.DATABASE_KEYS.TABLE_VOTO_NAME,
            columns,
            where,
            whereArgs,
            null,
            null,
            null
        )

        cursor.use {
            voto = if(cursor.moveToNext()){
                Voto(cursor.getString(0), cursor.getInt(1), cursor.getString(2))
            }else{
                null
            }
        }

        return voto
    }
}