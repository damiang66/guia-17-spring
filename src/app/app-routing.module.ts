import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VistainicioComponent } from './componente/vistainicio/vistainicio.component';

const routes: Routes = [
  {path:"noticia",component:VistainicioComponent}
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
