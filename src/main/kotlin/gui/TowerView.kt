package gui

import game.HanoiTower

interface TowerView {
    fun show(tower: HanoiTower): String
}
