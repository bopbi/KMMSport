import SwiftUI
import shared


struct ContentView: View {
    @State private var showingAlert = false
    @State private var searchText = ""
    
    func greet() -> String {
        
        doSearch()
        
        return Greeting().greeting()
    }
    
    func doSearch() {
        let sportsAPI = SportsAPIImpl()
        sportsAPI
            .searchTeamWrap(keyword: "arsenal")
            .subscribe(isThreadLocal: false, onSubscribe: nil, onError: nil) { (value: TeamSearchResult) in
                showingAlert = true
                searchText = value.teams.first?.strTeam ?? ""
            }
    }
    
    var body: some View {
        Text(greet())
            .alert(isPresented: $showingAlert, content: {
                Alert(title: Text(searchText))
            })
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
