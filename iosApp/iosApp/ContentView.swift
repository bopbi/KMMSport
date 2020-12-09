import SwiftUI

struct ContentView: View {
    @ObservedObject var viewModel = ViewModel()
    
    var body: some View {
        Text("Hi")
            .alert(isPresented: $viewModel.showingAlert, content: {
                Alert(title: Text(viewModel.searchText))
            })
            .onAppear {
                viewModel.doSearch()
            }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
