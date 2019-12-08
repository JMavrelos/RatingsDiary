package gr.blackswamp.ratingsdiary.data.db

import gr.blackswamp.ratingsdiary.data.db.daos.EntryDao

interface IDatabase {
    val dao: EntryDao
}
