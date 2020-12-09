//
//  ViewModel.swift
//  iosApp
//
//  Created by Bobby Prabowo on 09/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import shared

class ViewModel: ObservableObject {
    @Published var showingAlert = false
    @Published var searchText = ""
    
    private let searchTeam : SearchTeam
    init() {
        let sportsAPI = SportsAPIImpl()
        let sportsRepository = SportsRepositoryImpl(sportsAPI: sportsAPI)
        searchTeam = SearchTeamImpl(sportsRepository: sportsRepository)
    }
    
    func doSearch() {
        self.searchTeam.execute(keyword: "Arsenal") { (teamSearchResult) in
            self.showingAlert = true
            self.searchText = teamSearchResult.teams.first?.strTeam ?? "not found"
        } fail: { (error) in
            self.showingAlert = true
            self.searchText = error.message ?? "error"
        }
    }

}
