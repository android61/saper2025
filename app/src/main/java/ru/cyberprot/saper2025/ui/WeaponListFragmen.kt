package ru.cyberprot.saper2025.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.cyberprot.saper2025.R
import ru.cyberprot.saper2025.adapter.WeaponAdapter
import ru.cyberprot.saper2025.dto.Weapon
import ru.cyberprot.saper2025.repository.WeaponRepository
import ru.cyberprot.saper2025.viewmodel.WeaponListViewModel

class WeaponListFragment : Fragment() {

    companion object {
        private const val ARG_TYPE = "type"
        fun newInstance(type: String): WeaponListFragment {
            val fragment = WeaponListFragment()
            val args = Bundle().apply { putString(ARG_TYPE, type) }
            fragment.arguments = args
            return fragment
        }
    }
    private val viewModel: WeaponListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_weapon_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val type = arguments?.getString(ARG_TYPE) ?: "ppMines"

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = WeaponAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Подписываемся на изменения данных в ViewModel
        viewModel.items.observe(viewLifecycleOwner) { weaponList ->
            // Когда данные приходят, обновляем адаптер
            adapter.submitList(weaponList)
        }

        // Запускаем загрузку данных
        viewModel.loadWeapons(requireContext(), type)

    }
    fun filterList(query: String) {
        viewModel.filterWeapons(query)
    }
}