package gr.blackswamp.ratingsdiary.ui.commands

import gr.blackswamp.ratingsdiary.ui.model.EntryVO

sealed class ScreenCommand {
    sealed class showNewEntryDialog() : ScreenCommand()
    sealed class showEntry(val entry: EntryVO) : ScreenCommand()
}